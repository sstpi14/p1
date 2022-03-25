const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const userId = urlParams.get('userId');
console.log(userId);

window.onload = async function(){
    let response = await fetch(`http://localhost:9004/1/${userId}/reimbursements`);
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
        var table = document.getElementById("ticket-table");
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
        cell6.innerText = resolver_Id
        cell7.innerText = status_Id;
        cell8.innerText = type_Id;



        console.log(dataset[0].statusId, dataset[0].typeId);
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