function validatePassword(password) {
    const re = 
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    return re.test(String(password).toLowerCase());
}
export { validatePassword }

// 영문 숫자 특스문자를 적어도 1개 이상 포함한 8자리 이상