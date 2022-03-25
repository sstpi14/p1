let user;

window.onload = async function(){
    let response = await fetch("http://localhost:9004/checksession");

    let responseBody = await response.json();

    if(!responseBody.success){
        window.location = "../";
    }

    if(responseBody.data.roleId == "1"){
        window.location = "../../employee/dashboard"
    }else if(responseBody.data.roleId == "3"){
        window.location = "../../admin/dashboard"
    }

    user = responseBody.data;
}