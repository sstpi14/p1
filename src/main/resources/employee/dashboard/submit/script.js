const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const userId = urlParams.get('userId');
console.log(userId);

document.getElementById("submit-ticket-form").addEventListener("submit", async function register(event) {
    event.preventDefault();

    let amountInputElem = document.getElementById("amount");
    let descriptionInputElem = document.getElementById("description");
    var type_value;

    if(document.getElementById("lodging-radio").checked){
        type_value = document.getElementById("lodging-radio");
    } else if (document.getElementById("food-radio").checked) {
        type_value = document.getElementById("food-radio");
    } else if(document.getElementById("travel-radio").checked) {
        type_value = document.getElementById("travel-radio");
    }
    
    let typeIdInputElem = type_value;

    let reimbursement = {
        amount: amountInputElem.value,
        description: descriptionInputElem.value,
        authorId: userId,
        typeId: typeIdInputElem.value
    }

    let response = await fetch("http://localhost:9004/1/new-reimbursement", {
        method: "POST",
        body: JSON.stringify(reimbursement)
    })

    let responseBody = await response.json();

    if(responseBody.success == false) {
        let messageElem=document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{
        window.location = "../tickets/?userId=" + userId
    }

})