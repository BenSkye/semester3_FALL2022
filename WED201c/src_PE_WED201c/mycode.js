function check() {
    var name = document.getElementById('name').value;
    var phone = document.getElementById('phone').value;
    var email = document.getElementById('email').value;

    var mess = "Đừng để trống \n";
    if (name == '') {
        mess += "Tên ";
    }
    if (phone == '') {
        mess += "SĐT ";
    }
    if (email == '') {
        mess += "Email ";
    }

    if (name == "" || phone == "" || email == "") {
        alert(mess);
    }else{
        alert('OK!')
    }

}


function validate() {
    var name2 = document.getElementById('name2').value;
    var email2 = document.getElementById('email2').value;
    var namtotnghiep = document.getElementById('namtotnghiep').value;
    var math = document.getElementById('math').value;
    var physical = document.getElementById('physical').value;
    var english = document.getElementById('english').value;

    var check1 = document.getElementById('check1').checked;
    var check2 = document.getElementById('check2').checked;
    var check3 = document.getElementById('check3').checked;

    var mess = "Đừng để trống \n";
    if (name2 == '') {
        mess += "Tên ";
    }
    if (email2 == '') {
        mess += "Email ";
    }

    if (name2 == "" || email2 == "") {
        alert(mess);
        return;
    }

    var text = "";
    text += "Name" + name2;
    text += "\nemail:" + email2;
    text += "\nNăm tốt nghiệp: " + namtotnghiep;
    text += "\nĐiểm toán: " + math;
    text += "\nĐiểm lý:" + physical;
    text += "\nĐiểm Anh" + english;

    if (check1 || check2 || check3) {
        text += "\nKỹ năng: ";
    }
    if (check1) {
        text += "\nTư duy logic tốt"
    }
    if (check2) {
        text += "\nCó khiếu thẩm mỹ"
    } if (check3) {
        text += "\nNhạy bén"
    }


    document.getElementById('text-area').style.display = 'block';
    document.getElementById('text-area').value = text;

}


function validate2() {
    var name2 = document.getElementById('name2').value;
    var email2 = document.getElementById('email2').value;
    var namtotnghiep = document.getElementById('namtotnghiep').value;
    var math = document.getElementById('math').value;
    var physical = document.getElementById('physical').value;
    var english = document.getElementById('english').value;

    var check1 = document.getElementById('check1').checked;
    var check2 = document.getElementById('check2').checked;
    var check3 = document.getElementById('check3').checked;

    var mess = "Đừng để trống \n";
    if (name2 == '') {
        mess += "Tên ";
    }
    if (email2 == '') {
        mess += "Email ";
    }

    if (name2 == "" || email2 == "") {
        alert(mess);
        return;
    }

    var text = "";
    text += "<p>Name:" + name2 + "</p>";
    text += "<p>email:" + email2 + "</p>";
    text += "<p>Năm tốt nghiệp: " + namtotnghiep+ "</p>";
    text += "<p>Điểm toán: " + math+ "</p>";
    text += "<p>Điểm lý:" + physical+ "</p>";
    text += "<p>Điểm Anh" + english+ "</p>";

    if (check1 || check2 || check3) {
        text += "<p>Kỹ năng: </p>";
    }
    if (check1) {
        text += "<p>Tư duy logic tốt</p>"
    }
    if (check2) {
        text += "<p>Có khiếu thẩm mỹ</p>"
    } if (check3) {
        text += "<p>Nhạy bén</p>"
    }



    var myWindow = window.open("", "", "width=500,height=500");
    myWindow.document.write(text);
}