package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import DAO.CustomerDAO;
import DTO.Customer;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/")
@MultipartConfig(maxFileSize = 1024 * 1024 * 50, location = "/c:/Temp/img")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO dao;
	private ServletContext ctx;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new CustomerDAO();
		ctx = getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();
		String site = null;

		System.out.println("command: " + command);

		switch (command) {
		case "/index":
			site = getList(request);
			break;
		case "/view":
			site = getView(request);
			break;
		case "/regist":
			site = "regist.jsp";
			break;
		case "/insert":
			site = insertCustomer(request);
			break;
		case "/edit":
			site = getViewForEdit(request);
			break;
		case "/update":
			site = updateCustomer(request);
			break;
		case "/delete":
			site = deleteCustomer(request);
			break;
		}

		if (site != null && site.startsWith("redirect:/")) {
			String rview = site.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else if (site != null) {
			ctx.getRequestDispatcher("/" + site).forward(request, response);
		} else {
			// site 변수가 null인 경우, 인덱스 페이지로 리다이렉트하도록 처리
			response.sendRedirect("index");
		}
	}

	public String getList(HttpServletRequest request) {
		List<Customer> list;

		try {
			list = dao.getList();
			request.setAttribute("customerList", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "고객 목록을 정상적으로 불러오지 못했습니다.");
		}
		return "index.jsp";
	}

	public String getView(HttpServletRequest request) {
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		try {
			Customer customer = dao.getView(customerId);
			request.setAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "고객 정보를 불러오는 중 오류가 발생했습니다.");
		}

		return "view.jsp";
	}

	public String getViewForEdit(HttpServletRequest request) {
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		try {
			Customer customer = dao.getView(customerId);
			request.setAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "고객 정보를 불러오는 중 오류가 발생했습니다.");
		}

		return "edit.jsp";
	}

	public String insertCustomer(HttpServletRequest request) {
		Customer customer = new Customer();
		try {
			BeanUtils.populate(customer, request.getParameterMap());

			// 이미지 업로드 처리
			Part filePart = request.getPart("file");
			if (filePart != null && filePart.getSize() > 0) {
				String fileName = getFilename(filePart);
				String uploadPath = "/img/"; // 이미지 저장 경로
				filePart.write(fileName); // 이미지 저장
				customer.setImg("/img/" + fileName); // Customer 객체에 이미지 파일명 설정
			}

			dao.insertCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String encodeName = URLEncoder.encode("고객 정보를 정상적으로 등록하지 못했습니다.", "UTF-8");
				return "redirect:/index?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/index"; // 등록 성공 시에는 index 페이지로 리다이렉트
	}

	public String updateCustomer(HttpServletRequest request) {
		Customer customer = new Customer();
		try {
			BeanUtils.populate(customer, request.getParameterMap());

			// 이미지 업로드 처리
			Part filePart = request.getPart("file");
			if (filePart != null && filePart.getSize() > 0) {
				String fileName = getFilename(filePart);
				String uploadPath = "/img/"; // 변경된 이미지 저장 경로
				filePart.write(fileName); // 이미지 저장
				customer.setImg("/img/" + fileName); // Customer 객체에 이미지 파일명 설정
			}

			dao.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String encodeName = URLEncoder.encode("고객 정보를 정상적으로 수정하지 못했습니다.", "UTF-8");
				return "redirect:/view?customerId=" + customer.getCustomer_id() + "&error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/view?customerId=" + customer.getCustomer_id(); // 수정 성공 시에는 해당 고객의 정보 페이지로 리다이렉트
	}

	public String deleteCustomer(HttpServletRequest request) {
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		try {
			dao.deleteCustomer(customerId);
			return "redirect:/index"; // 삭제 성공 시에는 index 페이지로 리다이렉트
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String encodeName = URLEncoder.encode("고객 정보를 정상적으로 삭제하지 못했습니다.", "UTF-8");
				return "redirect:/index?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/"; // 삭제가 실패한 경우에도 인덱스 페이지로 이동하도록 설정
	}

	private String getFilename(Part part) {
		String fileName = null;
		// 파일이름이 들어있는 헤더 영역을 가지고 옴
		String header = part.getHeader("content-disposition");

		// form-data; name="img"; filename="사진5.jpg"
		System.out.println("Header =>" + header);

		// 파일 이름이 들어있는 속성부분의 시작위치(인덱스 번호)를 가지고 옴
		int start = header.indexOf("filename=");
		// 쌍따옴표 사이의 값 부분만 가지고 온다
		fileName = header.substring(start + 10, header.length() - 1);

		return fileName;
	}
}