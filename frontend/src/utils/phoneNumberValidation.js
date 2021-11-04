function validatePhone(phone) {
	const re =
		/\d/;
	return re.test(String(phone).toLowerCase());
}

export { validatePhone };
 // 한국 전화번호 형식