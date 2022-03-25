window.onload = async function(){
    let response = await fetch(`http://localhost:9004/2/reimbursements`);
    let responseBody = await response.json();
    const dataset = responseBody.data;
    var arrayLength = dataset.length;

    let response2 = await fetch(`http://localhost:9004/2/1/status`);
    let responseBody2 = await response2.json();
    const dataset2 = responseBody2.data;
    var arrayLength2 = dataset2.length;

    let response3 = await fetch(`http://localhost:9004/2/2/status`);
    let responseBody3 = await response3.json();
    const dataset3 = responseBody3.data;
    var arrayLength3 = dataset3.length;

    let response4 = await fetch(`http://localhost:9004/2/3/status`);
    let responseBody4 = await response4.json();
    const dataset4 = responseBody4.data;
    var arrayLength4 = dataset4.length;

    //getting all users to be able to have last name and first name for each userId
    let response5 = await fetch("http://localhost:9004/allusers")
    let responseBody5 = await response5.json();
    const dataset5 = responseBody5.data;
    let obj = dataset5.find(o => o.userId == 1);
    console.log(obj);
    
    //for loop for all reimbursements
    for (var i = 0; i < arrayLength; i++) {
        //find the table with id
        var table = document.getElementById("ticket-tablea");
        //creating rows through the for loop
        var row = table.insertRow(i);
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
            let resolver = dataset5.find(o => o.userId == resolver_Id);
            resolver_Id = resolver.lastname + ", " + resolver.firstname;
        }

        //made authorId (int) become author_Id (lastname, firstname)
        var author_Id = dataset[i].authorId;
        if (author_Id == 0) {
            author_Id == "N/A";
        } else {
            let author = dataset5.find(o => o.userId == author_Id);
            author_Id = author.lastname + ", " + author.firstname;
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
    //for loop for all pending reimbursements
    for (var i = 0; i < arrayLength2; i++) {
        //find the table with id
        var table = document.getElementById("ticket-tableb");
        //creating rows through the for loop
        var row = table.insertRow(i);
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
        var submittedDate = dataset2[i].dateSubmitted ? new Date(dataset2[i].dateSubmitted).toTimeString() + new Date(dataset2[i].dateSubmitted).toDateString(): "N/A";
        var resolvedDate = dataset2[i].dateResolved ? new Date(dataset2[i].dateResolved).toTimeString() + new Date(dataset2[i].dateResolved).toDateString(): "N/A";

        var status_Id = dataset2[i].statusId;
        if(status_Id == 1) {
            status_Id = "Pending";
        } else if(status_Id == 2) {
            status_Id = "Approved";
        } else if(status_Id == 3) {
            status_Id = "Denied";
        }

        var type_Id = dataset2[i].typeId;
        if(type_Id == 1) {
            type_Id = "Lodging";
        } else if(type_Id == 2) {
            type_Id = "Food";
        } else if(type_Id == 3) {
            type_Id = "Travel";
        }
        
        //made resolverId (int) become resolver_Id (lastname, firstname)  
        var resolver_Id = dataset2[i].resolverId;
        if (resolver_Id == 0){
            resolver_Id = "N/A";
        } else {
            let resolver = dataset5.find(o => o.userId == resolver_Id);
            resolver_Id = resolver.lastname + ", " + resolver.firstname;
        }

        //made authorId (int) become author_Id (lastname, firstname)
        var author_Id = dataset2[i].authorId;
        if (author_Id == 0) {
            author_Id == "N/A";
        } else {
            let author = dataset5.find(o => o.userId == author_Id);
            author_Id = author.lastname + ", " + author.firstname;
        }

        //fix amounts to be similar to currency
        var formatter = new Intl.NumberFormat("en-US", {
            style: "currency",
            currency: "USD"
        });

        cell1.innerText = dataset2[i].reimbId;
        cell2.innerText = formatter.format(dataset2[i].amount);
        cell3.innerText = submittedDate;
        cell4.innerText = resolvedDate;
        cell5.innerText = dataset2[i].description;
        cell6.innerText = dataset2[i].authorId;
        cell7.innerText = resolver_Id;
        cell8.innerText = status_Id;
        cell9.innerText = type_Id;



        console.log(dataset2[0].statusId, dataset2[0].typeId);
    }

    //for loop for approved reimbursements
    for (var i = 0; i < arrayLength3; i++) {
        //find the table with id
        var table = document.getElementById("ticket-tablec");
        //creating rows through the for loop
        var row = table.insertRow(i);
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
        var submittedDate = dataset3[i].dateSubmitted ? new Date(dataset3[i].dateSubmitted).toTimeString() + new Date(dataset3[i].dateSubmitted).toDateString(): "N/A";
        var resolvedDate = dataset3[i].dateResolved ? new Date(dataset3[i].dateResolved).toTimeString() + new Date(dataset3[i].dateResolved).toDateString(): "N/A";

        var status_Id = dataset3[i].statusId;
        if(status_Id == 1) {
            status_Id = "Pending";
        } else if(status_Id == 2) {
            status_Id = "Approved";
        } else if(status_Id == 3) {
            status_Id = "Denied";
        }

        var type_Id = dataset3[i].typeId;
        if(type_Id == 1) {
            type_Id = "Lodging";
        } else if(type_Id == 2) {
            type_Id = "Food";
        } else if(type_Id == 3) {
            type_Id = "Travel";
        }
        
        //made resolverId (int) become resolver_Id (lastname, firstname)  
        var resolver_Id = dataset3[i].resolverId;
        if (resolver_Id == 0){
            resolver_Id = "N/A";
        } else {
            let resolver = dataset5.find(o => o.userId == resolver_Id);
            resolver_Id = resolver.lastname + ", " + resolver.firstname;
        }

        //made authorId (int) become author_Id (lastname, firstname)
        var author_Id = dataset3[i].authorId;
        if (author_Id == 0) {
            author_Id == "N/A";
        } else {
            let author = dataset5.find(o => o.userId == author_Id);
            author_Id = author.lastname + ", " + author.firstname;
        }

        //fix amounts to be similar to currency
        var formatter = new Intl.NumberFormat("en-US", {
            style: "currency",
            currency: "USD"
        });

        cell1.innerText = dataset3[i].reimbId;
        cell2.innerText = formatter.format(dataset3[i].amount);
        cell3.innerText = submittedDate;
        cell4.innerText = resolvedDate;
        cell5.innerText = dataset3[i].description;
        cell6.innerText = dataset3[i].authorId;
        cell7.innerText = resolver_Id;
        cell8.innerText = status_Id;
        cell9.innerText = type_Id;



        console.log(dataset2[0].statusId, dataset2[0].typeId);
    }
    //for loop for all denied reimbursements
    for (var i = 0; i < arrayLength4; i++) {
        //find the table with id
        var table = document.getElementById("ticket-tabled");
        //creating rows through the for loop
        var row = table.insertRow(i);
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
        var submittedDate = dataset4[i].dateSubmitted ? new Date(dataset4[i].dateSubmitted).toTimeString() + new Date(dataset4[i].dateSubmitted).toDateString(): "N/A";
        var resolvedDate = dataset4[i].dateResolved ? new Date(dataset4[i].dateResolved).toTimeString() + new Date(dataset4[i].dateResolved).toDateString(): "N/A";

        var status_Id = dataset4[i].statusId;
        if(status_Id == 1) {
            status_Id = "Pending";
        } else if(status_Id == 2) {
            status_Id = "Approved";
        } else if(status_Id == 3) {
            status_Id = "Denied";
        }

        var type_Id = dataset4[i].typeId;
        if(type_Id == 1) {
            type_Id = "Lodging";
        } else if(type_Id == 2) {
            type_Id = "Food";
        } else if(type_Id == 3) {
            type_Id = "Travel";
        }
        
        //made resolverId (int) become resolver_Id (lastname, firstname)  
        var resolver_Id = dataset4[i].resolverId;
        if (resolver_Id == 0){
            resolver_Id = "N/A";
        } else {
            let resolver = dataset5.find(o => o.userId == resolver_Id);
            resolver_Id = resolver.lastname + ", " + resolver.firstname;
        }

        //made authorId (int) become author_Id (lastname, firstname)
        var author_Id = dataset4[i].authorId;
        if (author_Id == 0) {
            author_Id == "N/A";
        } else {
            let author = dataset5.find(o => o.userId == author_Id);
            author_Id = author.lastname + ", " + author.firstname;
        }

        //fix amounts to be similar to currency
        var formatter = new Intl.NumberFormat("en-US", {
            style: "currency",
            currency: "USD"
        });

        cell1.innerText = dataset4[i].reimbId;
        cell2.innerText = formatter.format(dataset4[i].amount);
        cell3.innerText = submittedDate;
        cell4.innerText = resolvedDate;
        cell5.innerText = dataset4[i].description;
        cell6.innerText = dataset4[i].authorId;
        cell7.innerText = resolver_Id;
        cell8.innerText = status_Id;
        cell9.innerText = type_Id;



        console.log(dataset2[0].statusId, dataset2[0].typeId);
    }
}

function sortTableByColumn(table, column, asc = true) {
    const dirModifier = asc ? 1 : -1;
    const tBody = table.tBodies[0];
    const rows = Array.from(tBody.querySelectorAll("tr"));

    // Sort each row
    const sortedRows = rows.sort((a, b) => {
        const aColText = a.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim();
        const bColText = b.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim();

        return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
    });

    // Remove all existing TRs from the table
    while (tBody.firstChild) {
        tBody.removeChild(tBody.firstChild);
    }

    // Re-add the newly sorted rows
    tBody.append(...sortedRows);

    // Remember how the column is currently sorted
    table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
    table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-asc", asc);
    table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-desc", !asc);
}

document.querySelectorAll(".table-sortable th").forEach(headerCell => {
    headerCell.addEventListener("click", () => {
        const tableElement = headerCell.parentElement.parentElement.parentElement;
        const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
        const currentIsAscending = headerCell.classList.contains("th-sort-asc");

        sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
    });
});

function viewPending() {
    var x = document.getElementById("pending-container")
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
function viewApproved() {
    var x = document.getElementById("approved-container")
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
function viewDenied() {
    var x = document.getElementById("denied-container")
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
function viewAll() {
    var x = document.getElementById("all-container")
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}