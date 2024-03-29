<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객 등록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col">
				<h2 class="mb-4">고객 등록</h2>
				<form name="frm" method="post" action="insert" enctype="multipart/form-data">
					<div class="mb-3">
						<input type="text" class="form-control" placeholder="이름" name="name" maxlength="50">
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" placeholder="주소" name="address" maxlength="100">
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" placeholder="전화번호" name="phone" maxlength="20">
					</div>
					<div class="mb-3">
						<select class="form-select" name="gender">
							<option selected>성별을 선택해주세요</option>
							<option value="남성">남성</option>
							<option value="여성">여성</option>
						</select>
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" placeholder="나이" name="age">
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" placeholder="보유한 포인트" name="point">
					</div>
					<div class="mb-3">
						<select class="form-select" name="grade">
							<option selected>등급을 선택해주세요</option>
							<option value="bronze">브론즈</option>
							<option value="silver">실버</option>
							<option value="gold">골드</option>
							<option value="VIP">VIP</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="file" class="form-label">프로필 이미지 업로드</label>
						<input type="file" class="form-control" id="file" name="file">
					</div>
					<div class="mb-3">
						<button type="submit" class="btn btn-primary me-3">등록</button>
						<a href="index" class="btn btn-outline-secondary">취소</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="./js/script.js"></script>
</body>
</html>
