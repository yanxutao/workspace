function check(form)
{
	if (form.username.value == null || form.username.value == "") {
		alert('Please input username!');
		return false;
	} else if (form.password.value == null || form.password.value == "") {
		alert('Please input password!');
		return false;
	} else {
		return true;
	}
}