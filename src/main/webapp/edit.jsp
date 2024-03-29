<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 정보 수정</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">고객 정보 수정</h2>
		<form name="frm" method="post" action="update"
			enctype="multipart/form-data">
			<div class="mb-3">
				<label for="customerId" class="form-label">고객 번호</label> <input
					type="text" class="form-control" id="customerId" name="customer_id"
					value="${customer.customer_id}" readonly>
			</div>
			<div class="mb-3">
				<label for="name" class="form-label">이름</label> <input type="text"
					class="form-control" id="name" name="name" maxlength="50"
					value="${customer.name}">
			</div>
			<div class="mb-3">
				<label for="address" class="form-label">주소</label> <input
					type="text" class="form-control" id="address" name="address"
					value="${customer.address}">
			</div>
			<div class="mb-3">
				<label for="phone" class="form-label">전화번호</label> <input
					type="text" class="form-control" id="phone" name="phone"
					value="${customer.phone}">
			</div>
			<div class="mb-3">
				<label for="gender" class="form-label">성별</label> <select
					class="form-select" id="gender" name="gender">
					<option value="남성" ${customer.gender eq '남성' ? 'selected' : ''}>남성</option>
					<option value="여성" ${customer.gender eq '여성' ? 'selected' : ''}>여성</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="age" class="form-label">나이</label> <input type="text"
					class="form-control" id="age" name="age" value="${customer.age}">
			</div>
			<div class="mb-3">
				<label for="point" class="form-label">포인트</label> <input type="text"
					class="form-control" id="point" name="point"
					value="${customer.point}">
			</div>
			<div class="mb-3">
				<label for="grade" class="form-label">등급</label> <select
					class="form-select" id="grade" name="grade">
					<option value="bronze"
						${customer.grade eq 'bronze' ? 'selected' : ''}>브론즈</option>
					<option value="silver"
						${customer.grade eq 'silver' ? 'selected' : ''}>실버</option>
					<option value="gold" ${customer.grade eq 'gold' ? 'selected' : ''}>골드</option>
					<option value="VIP" ${customer.grade eq 'VIP' ? 'selected' : ''}>VIP</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="file" class="form-label">프로필 이미지 업로드</label> <input
					type="file" class="form-control" id="file" name="file">
				<c:if test="${not empty customer.img}">
					<img src="${customer.img}" alt="프로필 이미지" class="mt-2 img-fluid"
						style="max-width: 100px;">
				</c:if>
				<input type="hidden" name="origin_img" value="${customer.img}">
			</div>
			<button type="submit" class="btn btn-primary">수정</button>
			<a href="index" class="btn btn-secondary">취소</a>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="./js/script.js"></script>
</body>
</html>
