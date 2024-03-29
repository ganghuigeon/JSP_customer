<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객 리스트</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">고객 리스트</h1>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>이름</th>
                        <th>주소</th>
                        <th>전화번호</th>
                        <th>성별</th>
                        <th>나이</th>
                        <th>포인트</th>
                        <th>등급</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="customer" items="${customerList}">
                        <tr>
                            <td>${customer.customer_id}</td>
                            <td><a href="view?customerId=${customer.customer_id}">${customer.name}</a></td>
                            <td>${customer.address}</td>
                            <td>${customer.phone}</td>
                            <td>${customer.gender}</td>
                            <td>${customer.age}</td>
                            <td>${customer.point}</td>
                            <td>${customer.grade}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="text-center">
            <button type="button" class="btn btn-outline-secondary">
                <a href="regist" class="text-decoration-none">고객 등록하기</a>
            </button>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        <c:if test="${error != null}">
            alert("${error}");
        </c:if>
        <c:if test="${param.error != null}">
            alert("${param.error}");
        </c:if>
    </script>
</body>
</html>
