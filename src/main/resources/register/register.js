document.getElementById("register-form").addEventListener("submit", async function register(event) {
    event.preventDefault();

    let usernameInputElem = document.getElementById("username");
    let passwordInputElem = document.getElementById("password");
    let firstnameInputElem = document.getElementById("firstname");
    let lastnameInputElem = document.getElementById("lastname");
    let emailInputElem = document.getElementById("emailaddress");
    var role_value;

    if(document.getElementById("employee-radio").checked){
        role_value = document.getElementById("employee-radio");
    } else if (document.getElementById("financialmanager-radio").checked) {
        role_value = document.getElementById("financialmanager-radio");
    }
    
    let roleIdInputElem = role_value;

    let user = {
        username: usernameInputElem.value,
        password: passwordInputElem.value,
        firstname: firstnameInputElem.value,
        lastname: lastnameInputElem.value,
        email: emailInputElem.value,
        roleId: roleIdInputElem.value
    }

    let response = await fetch("http://localhost:9004/newuser", {
        method: "POST",
        body: JSON.stringify(user)
    })

    let responseBody = await response.json();

    if(responseBody.success == false) {
        let messageElem=document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{
        console.log("Register Successful", responseBody.data)
        let messageElem=document.getElementById("message")
        messageElem.innerText = "Register Successful"
    }

})