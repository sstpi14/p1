let user;


window.onload = async function(){
    let response = await fetch("http://localhost:9004/checksession");

    let responseBody = await response.json();

    if(!responseBody.success){
        window.location = "../";
    }

    if(responseBody.data.roleId == "2"){
        window.location = "../../financialmanager/dashboard"
    }else if(responseBody.data.roleId == "3"){
        window.location = "../../admin/dashboard"
    }else/* {
        window.location = `?userId=` + responseBody.data.userId;
    } */

    user = responseBody.data;
    console.log(responseBody.data);
    
    var a = document.getElementById('path-container').getElementsByTagName('a'), length = a.length;
    for(var i=0; i< length; i++){
    a[i].href += `?userId=${responseBody.data.userId}`;
}
}

