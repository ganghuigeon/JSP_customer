<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 상세 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="container">
		<div class="mt-4 mb-3">
			<h3>고객 상세 페이지</h3>
		</div>
		<div class="card mb-3">
			<div class="row g-0">
				<div class="col-md-4">
					<c:if test="${not empty customer.img}">
						<img src="${customer.img}" alt="고객 이미지" class="img-fluid">
					</c:if>
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">${customer.name}님의정보</h5>
						<p class="card-text">고객 번호: ${customer.customer_id}</p>
						<p class="card-text">이름: ${customer.name}</p>
						<p class="card-text">나이: ${customer.age}</p>
						<p class="card-text">성별: ${customer.gender}</p>
						<p class="card-text">주소: ${customer.address}</p>
						<p class="card-text">전화번호: ${customer.phone}</p>
						<p class="card-text">등급: ${customer.grade}</p>
						<p class="card-text">포인트: ${customer.point}</p>
						<div class="edit-delete-links">
							<a href="edit?customerId=${customer.customer_id}"
								class="btn btn-sm btn-primary">수정</a> <a
								href="delete?customerId=${customer.customer_id}"
								class="btn btn-sm btn-danger">삭제</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<button type="button" class="btn btn-outline-secondary">
			<a href="index">목록</a>
		</button>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script>
    // request 객체에 error가 있을 경우 에러 메시지 출력
    <c:if test="${error != null}">
        alert("${error}");
    </c:if>
    <c:if test="${param.error != null}">
        alert("${param.error}");
    </c:if>
    </script>
	<script type="text/javascript" src="./js/script.js"></script>
</body>
</html>
