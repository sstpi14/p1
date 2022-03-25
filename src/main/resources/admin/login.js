document.getElementById("login-form").addEventListener("submit", async function login(event) {
    event.preventDefault();

    let usernameInputElem = document.getElementById("username");
    let passwordInputElem = document.getElementById("password");

    let user = {
        username: usernameInputElem.value,
        password: passwordInputElem.value,
        approved: "true"
    }

    let response = await fetch("http://localhost:9004/login3", {
        method: "POST",
        body: JSON.stringify(user)
    })

    let responseBody = await response.json();

    if(responseBody.success == false){
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message;
    }else{
        window.location = "./dashboard?userId=" + responseBody.data.userId
    }

})