const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const userId = urlParams.get('userId');
console.log(userId);

document.getElementById("view-ticket-form").addEventListener("submit", async function viewReimbursementbyReimbID(event) {
    event.preventDefault();

    let reimbIdInputElem = document.getElementById("reimbId");
    let reimbId = reimbIdInputElem.value;
    let response = await fetch(`http://localhost:9004/2/${reimbId}/reimbid`);
    let responseBody = await response.json();
    const dataset = responseBody.data;
    var arrayLength = dataset.length;

    //getting all users to be able to have last name and first name for each userId
    let response2 = await fetch("http://localhost:9004/allusers")
    let responseBody2 = await response2.json();
    const dataset2 = responseBody2.data;
    let obj = dataset2.find(o => o.userId == 1);
    console.log(obj);

    for (var i = 0; i < arrayLength; i++) {
        //find the table with id
        var table = document.getElementById("ticket-tablea");
        //creating rows through the for loop
        var row = table.insertRow(0);
        //creating cells based on number of arguments in reimbursement
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        var cell7 = row.insertCell(6);
        var cell8 = row.insertCell(7);
        var cell9 = row.insertCell(8);
        
        //fix date with toDateString();
        var submittedDate = dataset[i].dateSubmitted ? new Date(dataset[i].dateSubmitted).toTimeString() + new Date(dataset[i].dateSubmitted).toDateString(): "N/A";
        var resolvedDate = dataset[i].dateResolved ? new Date(dataset[i].dateResolved).toTimeString() + new Date(dataset[i].dateResolved).toDateString(): "N/A";
        var status_Id = dataset[i].statusId;
        if(status_Id == 1) {
            status_Id = "Pending";
        } else if(status_Id == 2) {
            status_Id = "Approved";
        } else if(status_Id == 3) {
            status_Id = "Denied";
        }

        var type_Id = dataset[i].typeId;
        if(type_Id == 1) {
            type_Id = "Lodging";
        } else if(type_Id == 2) {
            type_Id = "Food";
        } else if(type_Id == 3) {
            type_Id = "Travel";
        }
        
        //made resolverId (int) become resolver_Id (lastname, firstname) 
        var resolver_Id = dataset[i].resolverId;
        if (resolver_Id == 0){
            resolver_Id = "N/A";
        } else {
            let user = dataset2.find(o => o.userId == resolver_Id);
            resolver_Id = user.lastname + ", " + user.firstname;
        }
        //fix amounts to be similar to currency
        var formatter = new Intl.NumberFormat("en-US", {
            style: "currency",
            currency: "USD"
        });

        cell1.innerText = dataset[i].reimbId;
        cell2.innerText = formatter.format(dataset[i].amount);
        cell3.innerText = submittedDate;
        cell4.innerText = resolvedDate;
        cell5.innerText = dataset[i].description;
        cell6.innerText = dataset[i].authorId;
        cell7.innerText = resolver_Id;
        cell8.innerText = status_Id;
        cell9.innerText = type_Id;



        console.log(dataset[0].statusId, dataset[0].typeId);
    }
})
/* document.getElementById("approveordeny").addEventListener("submit", async function viewReimbursement(event) {
    event.preventDefault();
    let reimbIdInputElem = document.getElementById("reimbId2");
    var status_Id;
    if(document.getElementById("approve").checked){
        status_Id = document.getElementById("approved");
    } else if (document.getElementById("deny").checked) {
        status_Id = document.getElementById("deny");
    }
    let statusIdInputElem = status_Id;
    
    reimbursement = {
        statusId: statusIdInputElem,
        resolverId: 2,
        reimbId: reimbIdInputElem
    }
    let reimbId = reimbIdInputElem.value;
    let response = await fetch(`http://localhost:9004/2/update-reimbursement`, {
    method: "PUT",
    body: JSON.stringify(reimbursement)
    })
    let responseBody = await response.json();

    console.log(responseBody)
}) */

function viewReimbursement() {
    var x = document.getElementById("chosenticket-container")
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}