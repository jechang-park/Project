// 변수
const $pw = document.getElementById('pw');
const $pwError = document.getElementById('pwError');
const $pw_check = document.getElementById('pw_check');
const $pwChkError = document.getElementById('pwChkError');
const $nick = document.getElementById('nick');
const $nickError = document.getElementById('nickError');
const $id = document.getElementById('id');
const $idError = document.getElementById('idError');
const $codeBtn = document.getElementById('codeBtn');
const $emailError = document.getElementById('emailError');
const $directInput = document.getElementById('directInput');
const $emailSelect = document.getElementById('emailSelect');
const $email = document.getElementById('email');
const $naver = document.getElementById('naver');
const $google = document.getElementById('google');
const $daum = document.getElementById('daum');
const $finishBtn = document.getElementById('finishBtn');
const $man = document.getElementById('man');
const $women = document.getElementById('women');
const $age = document.getElementById('age');
const $checkboxIdentity = document.getElementById('checkboxIdentity');
const $checkboxAgree = document.getElementById('checkboxAgree');
const $timeLimit = document.getElementById('timeLimit');
const $completion = document.getElementById('completion');
const $agreeUrl = document.getElementById('agreeUrl');

//비밀번호 일치여부 확인

function pwChk() {
  const pwValue = $pw.value;
  const pwCheckValue = $pw_check.value;
  if (pwValue !== pwCheckValue && pwCheckValue.length > 0) {
  $pwChkError.classList.remove('positive');
     $pwChkError.classList.add('negative');
    $pwChkError.style.color = 'red';
    $pwChkError.innerHTML = '비밀번호가 동일하지 않습니다.';
    //확인하지 않았을시는 메세지 출력 안하기
  } else if (pwCheckValue.length == 0) {
    $pwChkError.innerHTML = ' ';
  } else {
  $pwChkError.classList.add('positive');
       $pwChkError.classList.remove('negative');
    $pwChkError.style.color = 'green';
    $pwChkError.innerHTML = '✔';
  }
}

$pw.addEventListener('input', pwChk);
$pw_check.addEventListener('input', pwChk);

//비밀번호 길이확인

function pwLengthChk() {
  const pwValue = $pw.value;
  if (pwValue.length < 7 && pwValue.length > 0) {
   $pwError.classList.remove('positive');
   $pwError.classList.add('negative');
    $pwError.style.color = 'red';
    $pwError.innerHTML = '패스워드 길이가 너무 짧습니다.';
  } else if (pwValue.length > 7 && pwValue.length < 21) {
 $pwError.classList.remove('negative');
  $pwError.classList.add('positive');
    $pwError.style.color = 'green';
       $pwError.innerHTML = '✔';
  } else if (pwValue.length > 21) {
     $pwError.classList.remove('positive');
        $pwError.classList.add('negative');
    $pwError.style.color = 'red';
    $pwError.innerHTML = '패스워드 길이가 너무 깁니다.';
  } else if (pwValue == '') {
    $pwError.innerHTML = '';
  }
}
$pw.addEventListener('input', pwLengthChk);

//닉네임 길이확인,입력값 확인
$nick.addEventListener('input', nickLengthChk);

function nickLengthChk() {
$nickError.classList.add('positive');
  const nickValue = $nick.value;
  if (nickValue.length > 0 && nickValue.length < 10) {

    nickInputCheck();
  }
  if (nickValue.length > 10) {
  $nickError.classList.remove('positive');
    $nickError.style.color = 'red';
    $nickError.innerHTML = '닉네임이 길어요';
  } else if (nickValue == '') {
    $nickError.innerHTML = '';
  }
}

function nickInputCheck() {
  const inputValue = $nick.value;
  const regex = /^[a-zA-Z0-9가-힣]*$/;
  if (!regex.test(inputValue)) {
    $nickError.classList.remove('positive');
    $nickError.style.color = 'red';
    $nickError.innerHTML = '잘못된입력입니다.';

  } else {

    $nickError.style.color = 'green';
    $nickError.innerHTML = '✔';
  }
}

//아이디 길이확인

function idLengthChk() {
  const idValue = $id.value;
  if (idValue.length < 6 && idValue.length > 0 ) {
    $idError.classList.remove('positive');
    $idError.classList.add('negative');
    $idError.style.color = 'red';
    $idError.innerHTML = '아이디 길이가 너무 짧습니다.';


  }  else if (idValue.length > 6 && idValue.length < 15) {
      $idError.classList.remove('negative');
        $idError.classList.add('positive');
        $idError.style.color = 'green';
        $idError.innerHTML = '✔';

      }
      else if (idValue.length > 15) {
    $idError.classList.remove('positive');
      $idError.classList.add('negative');
    $idError.innerHTML = '아이디 길이가 너무 깁니다.';
    $idError.style.color = 'red';
  } else if (idValue == '') {
    $idError.innerHTML = '';
  }
}
$id.addEventListener('input', idLengthChk);

// 이메일 선택시 자동완성

//인증버튼발송 버튼 활성화하기
function emailCheckInputs() {
  const emailCheck = $email.value.trim();
  if (
    (emailCheck !== '' &&
      emailCheck.includes('@') &&
      emailCheck.includes('naver.com')) ||
    emailCheck.includes('daum.net') ||
    emailCheck.includes('google.com')
  ) {
    $emailError.classList.add('positive');
    $emailError.style.color = 'green';
    $emailError.innerHTML = '✔';
    $codeBtn.disabled = false;
  } else {
    $codeBtn.disabled = true;
    $emailError.innerHTML = '';
  }
}

function emailSelectAuto() {
  const emailValue = $email.value;
  //직접 입력인 경우
  if ($directInput.selected) {
    $email.value = emailValue.replace(/.*/, '');
    $codeBtn.disabled = true;
    $emailError.innerHTML = '';
  }
  if (emailValue.indexOf('@') === -1 && emailValue.length > 0) {
    // '@' 문자가 없는 경우
    if ($naver.selected) {
      $email.value = emailValue + '@naver.com';
      emailCheckInputs();
    } else if ($google.selected) {
      $email.value = emailValue + '@google.com';
      emailCheckInputs();
    } else if ($daum.selected) {
      $email.value = emailValue + '@daum.net';
      emailCheckInputs();
    }
  } else {
    //'@' 문자가 있는경우
    if ($naver.selected) {
      $email.value = emailValue.replace(/@.*/, '@naver.com');
      emailCheckInputs();
    } else if ($google.selected) {
      $email.value = emailValue.replace(/@.*/, '@google.com');
      emailCheckInputs();
    } else if ($daum.selected) {
      $email.value = emailValue.replace(/@.*/, '@daum.net');
      emailCheckInputs();
    }
  }
}

$emailSelect.addEventListener('change', emailSelectAuto);

// 필수 기입사항 입력 완료시 회원가입 버튼 활성화,
// 선택입력 활성화
// 추가할것 : 필수 사항 제대로 입력시 버튼활성화

function CheckInputs() {
  const idCheck = $id.value.trim();
  const pwCheck = $pw.value.trim();
  const pw_check = $pw_check.value.trim();
  const nickCheck = $nick.value.trim();
  const emailCheck = $email.value.trim();

  if (
    $pwChkError.innerHTML == '✔' &&
    $pwError.innerHTML == '✔' &&
    $idError.innerHTML == '✔' &&
    $nickError.innerHTML == '✔' &&
    ((emailCheck.includes('@') && emailCheck.includes('.com')) ||
      emailCheck.includes('.net'))
  ) {
    $man.disabled = false;
    $women.disabled = false;
    $age.disabled = false;
    $checkboxIdentity.disabled = false;
    $checkboxAgree.disabled = false;
  } else {
    $man.disabled = true;
    $women.disabled = true;
    $age.disabled = true;
    $checkboxIdentity.disabled = true;
    $checkboxAgree.disabled = true;
  }
  if (
    idCheck !== '' &&
    pwCheck !== '' &&
    pw_check !== '' &&
    nickCheck !== '' &&
    emailCheck !== '' &&
    $checkboxIdentity.checked &&
    $checkboxAgree.checked
  ) {
    $finishBtn.disabled = false;
  } else {
    $finishBtn.disabled = true;
  }
}

function AllCheckInputs() {
  $id.addEventListener('input', CheckInputs);
  $pw.addEventListener('input', CheckInputs);
  $pw_check.addEventListener('input', CheckInputs);
  $nick.addEventListener('input', CheckInputs);
  $email.addEventListener('input', CheckInputs);
  $checkboxIdentity.addEventListener('change', CheckInputs);
  $checkboxAgree.addEventListener('change', CheckInputs);
}

AllCheckInputs();

// 인증코드받기 버튼 클릭시 타이머 동작,타이머 히든요소 false 로 변경

let minutes = 3;
let seconds = 0;
let timer;

function startTimer() {
  if (timer) {
    clearInterval(timer);
  }
  minutes = 3;
  seconds = 0;
  $completion.textContent = '인증완료';
  updateTimer();
  timer = setInterval(updateTimer, 1000);
  $timeLimit.hidden = false;
  $completion.hidden = false;
  $codeBtn.textContent = '인증코드재전송';
}

function updateTimer() {
  if (seconds === 0) {
    if (minutes === 0) {
      clearInterval(timer);
      $completion.textContent = '시간초과';
      $completion.disabled = true;
    } else {
      minutes--;
      seconds = 59;
    }
  } else {
    seconds--;
  }
  $timeLimit.textContent = `${minutes}:${seconds.toString().padStart(2, '0')} `;
}
//서비스 이용약관 창으로 뛰우기,일단 클릭시 체크박스 완료되게 설정

$agreeUrl.addEventListener('click', () => {
  const url = 'http://localhost:9080/members/Agree';
  const name = 'areeeUrl';
  window.open(url, name, 'width=800,height=800,left=600');
  //클릭시 체크박스 완료되게 설정 (임시)
  $checkboxAgree.checked = true;
});
const $identityUrl = document.getElementById('identityUrl');
$identityUrl.addEventListener('click', () => {
  const url = 'http://localhost:9080/members/Identity';
  const name = 'identityUrl';
  window.open(url, name, 'width=800,height=800,left=600');
  $checkboxIdentity.checked = true;
});

//아이디 허용 문자 체크

$id.addEventListener('input', function () {
  const inputValue = $id.value;
  const regex = /[^A-Za-z0-9]/g;
  if (regex.test(inputValue)) {
    $idError.style.color = 'red';
    $idError.innerHTML = '잘못된입력입니다.';
  }
});

//비밀번호 허용 문자 체크

$pw.addEventListener('input', function () {
  const inputValue = $pw.value;
  const regex = /[^A-Za-z0-9]/g;
  if (regex.test(inputValue)) {
    $pwError.style.color = 'red';
    $pwError.innerHTML = '잘못된입력입니다.';
  }
});

//비밀번호 체크 허용문자 체크

$pw_check.addEventListener('input', function () {
  const inputValue = $pw_check.value;
  const regex = /[^A-Za-z0-9]/g;
  if (regex.test(inputValue)) {
    $pwChkError.style.color = 'red';
    $pwChkError.innerHTML = '잘못된입력입니다.';
  }
});

//닉네임 허용 문자 체크

// $nick.addEventListener('input', function () {
//   const inputValue = $nick.value;
//   const regex = /[^ !@#$%^&*()~`<>?{}[\];"':\-+=| ]+/g;
//   if (!regex.test(inputValue)) {
//     $nickError.style.color = 'red';
//     $nickError.innerHTML = '잘못된입력입니다.';
//   }
// });

//이메일 허용 문자 체크,인증버튼 활성화

//인증버튼 활성화
//인증버튼발송 버튼 활성화하기

function emailCheckInputs() {
  const regex = /[^A-Za-z0-9@.]+/g;
  const emailCheck = $email.value.trim();
  const inputValue = $email.value;
  if (
    (emailCheck !== '' &&
      emailCheck.includes('@') &&
      emailCheck.includes('.com')) ||
    emailCheck.includes('.net')
  ) {
    $emailError.classList.add('positive');
    $codeBtn.disabled = false;
    $emailError.style.color = 'green';
    $emailError.innerHTML = '✔';
  } else {
    $codeBtn.disabled = true;
  }
}

$email.addEventListener('input', function () {
  const inputValue = $email.value;
  const regex = /[^A-Za-z0-9@. ]+/g;
  emailCheckInputs();
  if (regex.test(inputValue)) {
    $emailError.style.color = 'red';
    $emailError.innerHTML = '잘못된 입력입니다.';
    emailCheckInputs();
  } else if (
    (inputValue.includes('@') && inputValue.includes('.com')) ||
    inputValue.includes('.net')
  ) {
    $emailError.classList.add('positive');
    $emailError.style.color = 'green';
    $emailError.innerHTML = '✔';
    emailCheckInputs();
  } else {
    $emailError.innerHTML = '';
  }
});

//해야하는거 아이디 중복확인 데이터베이스 필요
// 닉네임 중복확인 데이터베이스 필요
// 아이디,비밀번호 영문,숫자 입력 체크 기능 대강완료
// 이메일 기본 기입방법과 일치하는지 체크 여부 대강완료
// 선택 제외 모든항목 기입시 회원가입 버튼 활성화 대강완료
// 올바른 이메일 입력시 인증코드 받기 버튼 활성화 대강완료
// 인증코드 발송 기능 API 찾기 스프링 필요
// 인증확인이 됬을경우 인증완료 버튼 활성화 스프링 필요
// 서비스 이용약관,개인정보 처리방치동의 했을시 체크버튼 활성화,창으로뛰우기 대강완료
// 취소시 올리셋 나가기
// 인증코드받기 버튼 누를시 타이머 동작기능 // 대강완료
// 나이부분에 년도,월 오늘날짜 이상으로 적어지는거 막기 // 대강완료
