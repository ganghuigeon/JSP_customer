//게시물 등록 전 체크
function chkForm() {
    var f = document.forms["frm"]; // 폼 요소 가져오기

    // 이름, 주소, 전화번호, 나이를 입력하지 않았을 경우 알림
    if (f.name.value == '' || f.address.value == '' || f.phone.value == '' || f.age.value == '') {
        alert("정보를 입력해주세요.");
        return false; // 폼 서브밋 막기
    }
    f.submit(); // 폼 서브밋
}
// 삭제 확인 메시지 표시 및 삭제 처리 함수
function confirmDelete(customer_id) {
    // 확인 창 표시
    if (confirm("삭제하시겠습니까?")) {
        // 확인을 눌렀을 때, 삭제 요청 URL 생성
        var url = "delete?customerId=" + customer_id;
        // 삭제 요청 URL로 이동
        window.location.href = url;
    } else {
        // 취소를 눌렀을 때, 아무 작업도 수행하지 않음
        return;
    }
}