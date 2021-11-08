window.onload = function () {
    getUserData();
    getTicketData();
    // sortTableByColumn(document.querySelector("table"), 6);
}



async function getTicketData()
{
	//console.log("inside get ticket data manager");
	
	const responsePayLoad = await fetch(`http://localhost:9001/api/user/manager/allreimbursementTicket`);
	
	const ourJSON = await responsePayLoad.json();
	
	loadTableData(ourJSON);
}


async function updateTicket(myobj)
{
	fetch(`http://localhost:9001/api/user/manager/allreimbursementTicket/updateTicket`, {


		method: `post`,
		body: JSON.stringify(myobj),
		headers: {
			'Content-Type': `application/json`
		}
	});
}


async function getUserData()
{
	const responsePayload =  await fetch(`http://localhost:9001/api/user/info`,{method : `POST`});

		
	
	const ourJSON = await responsePayload.json();;
	
	loadUserInfo(ourJSON);
}

async function logout()
{
    const responsePayload =  await fetch(`http://localhost:9001/logout`,{method : `POST`});
    window.location.reload();
}



let loadUserInfo = function(userInfo)
{
	//manipulate the DOM to add info about the current user
	
	let name = document.getElementById("userName");
	
	name.innerText = `Hello, ${userInfo.firstName.charAt(0).toUpperCase() + userInfo.firstName.slice(1)} ${userInfo.lastName.charAt(0).toUpperCase() + userInfo.lastName.slice(1)}`;
	
	
	//console.log(userInfo);
}










//purpose is to inject the data to DOM dynamic
function loadTableData(ticketData) {
    const tableBody = document.getElementById(`tableData`);
    let dataHTML = ``;
    //console.log(ticketData);
    for (let ticket in ticketData) // use creteelelment // createtextnode data from json //appendchild
    {
        let newTR = document.createElement("tr");
        // for(let data in ticket)
        // {
        //     console.log(data);
        // }

        let newTD1 = document.createElement("td");
        let newTD2 = document.createElement("td");
        let newTD3 = document.createElement("td");
        let newTD4 = document.createElement("td");
        let newTD5 = document.createElement("td");
        let newTD6 = document.createElement("td");
        let newTD8 = document.createElement("td");
        let newTD9 = document.createElement("td");


        let submittedDate = ticketData[ticket].reimbSubmitted.split(" ")[0];

        let text1 = document.createTextNode(ticketData[ticket].reimbId);
        let text2 = document.createTextNode(`$${ticketData[ticket].reimbAmount}`);
        let text3 = document.createTextNode(submittedDate);
        let text4 = document.createTextNode((ticketData[ticket].reimbResolved == null)? "unresolved" : ticketData[ticket].reimbResolved.split(" ")[0]);
        let text5 = document.createTextNode((ticketData[ticket].reimbDescription == null)? "empty" : ticketData[ticket].reimbDescription);
        let text6 = document.createTextNode(ticketData[ticket].reimbAuthor);
        let text8 = document.createTextNode(ticketData[ticket].reimbStatus);
        let text9 = document.createTextNode(ticketData[ticket].reimbType);

        newTD1.appendChild(text1);
        newTD2.appendChild(text2);
        newTD3.appendChild(text3);
        newTD4.appendChild(text4);
        newTD5.appendChild(text5);
        newTD6.appendChild(text6);
        newTD8.appendChild(text8);
        newTD9.appendChild(text9);

        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);
        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5);
        newTR.appendChild(newTD6);
        newTR.appendChild(newTD8);
        newTR.appendChild(newTD9);

        (ticketData[ticket].reimbStatusId == 2)? newTR.setAttribute("onClick", "getTicket(this.id)"): "";
        // newTR.setAttribute("onClick", "getTicket(this.id)");

        // let id = document.createAttribute(`id`, `${text1}`);
        newTR.setAttribute(`id`, `${ticketData[ticket].reimbId}`);
        newTR.setAttribute('class', 'ticketShadow');
        let body = document.getElementById('tableData');
        body.appendChild(newTR);

        //    dataHTML += `<tr><td>${ticket.reimbId}</td><td>$${ticket.reimbAmount}</td><td>${ticket.reimbSubmitted}</td>
        //                      <td>${ticket.reimbResolved}</td><td>${ticket.reimbDescription}</td>
        //                     <td>${ticket.reimbAuthor}</td><td>${ticket.reimbStatus}</td>
        //                     <td>${ticket.reimbType}</td></tr>`;
    }

    
    // tableBody.innerHTML = dataHTML;
    // console.log(dataHTML);
}



let getTicket = function (varId) {
    //console.log(varId);
    openNewReimbursementTicketModal(varId);
}


/**
 *  Sorts a html table
 * 
 * @param {HTMLTableElement} table the table to sort
 * @param {number} column the index of the column to sort
 * @param {boolean} asc determines if the sorting will be ascending 
 */
function sortTableByColumn(table, column, asc = true) {
    const dirModifier = asc ? 1 : -1;
    const tBody = table.tBodies[0];
    const rows = Array.from(tBody.querySelectorAll("tr"));

    //add a if else in here to make sure we dont try and sort on a number
    // 0, 1, 3 are numbers do not sort them with below code

    // Sort each row
    const sortedRows = rows.sort((a, b) => {
        const aColText = a.querySelector(`td:nth-child(${column + 1})`).textContent.trim().replace("-", "").replace("-", "");
        const bColText = b.querySelector(`td:nth-child(${column + 1})`).textContent.trim().replace("-", "").replace("-", "");
        // console.log(aColText);
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
    table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-asc", asc);
    table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-desc", !asc);
}

document.querySelectorAll(".table-sortable th").forEach(headerCell => {
    headerCell.addEventListener("click", () => {
        const tableElement = headerCell.parentElement.parentElement.parentElement;
        // console.log(tableElement);
        const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
        const currentIsAscending = headerCell.classList.contains("th-sort-asc");

        sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
    });
});
 

let ticketObj;

const openNewReimbursementTicketModal = function (ticketId) //open the modal that allows the user to enter input.... clear all previous input
{
    const modal = document.getElementById('modal');
    //should probably build this all in an object so I dont have to rewrite this twice.....:(
    document.getElementById("author").innerText = `Author ID: ${document.getElementById(ticketId).cells[5].innerText}`;
    document.getElementById("tickeId").innerText = `Ticket ID: ${document.getElementById(ticketId).cells[0].innerText}`;
    document.getElementById("amount").innerText = document.getElementById(ticketId).cells[1].innerText;
    document.getElementById("submitted").innerText = document.getElementById(ticketId).cells[2].innerText;
    document.getElementById("description").innerText = document.getElementById(ticketId).cells[4].innerText;
    document.getElementById("status").innerText = document.getElementById(ticketId).cells[6].innerText;
    document.getElementById("type").innerText = document.getElementById(ticketId).cells[7].innerText;
    buildUpdateTicketObj(document.getElementById(ticketId).cells[0].innerText);

    openModal(modal);
}


// int reimbId, int reimbStatusId, int reimbResolver
let acceptTicketButton = function () {
    const modal = document.getElementById('modal');

    ticketObj.reimbStatusId = 1;

    //console.log(ticketObj);
    
    updateTicket(ticketObj);
    window.location.reload();
    closeModal(modal);

}

let buildUpdateTicketObj = (id) => {
    ticketObj = { reimbId: id };

}
let denyTicketButton = (id) => {
    const modal = document.getElementById('modal');

    ticketObj.reimbStatusId = 0;

    //console.log(ticketObj);
    updateTicket(ticketObj);

    window.location.reload();
    closeModal(modal);
    
}




function openModal(modal, ticketID) {
    if (modal == null) {
        return
    }

    modal.classList.add('active'); //add the active css class to the modal class list
    overlay.classList.add('active'); //add the active css class to the overlay class list
}


function closeModal(modal) {
    if (modal == null) {
        return
    }

    modal.classList.remove('active');
    overlay.classList.remove('active');
}

function closeOpenModal() {
    const modal = document.getElementById(`modal`);

    closeModal(modal);
}



function searchFunction() {
    let input, filter, table, tr, td, i;

    input = document.getElementById("myInput");
    filter = input.value.toLowerCase();
    tBody = document.getElementById("tableData");
    tr = tBody.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        let rowContainsFiler = false;
        for (let j = 0; j < td.length; j++) {
            if (td[j]) 
            {

                textvalue = td[j].textContent || td[j].innerText;
                if (textvalue.toLowerCase().indexOf(filter) > -1) 
                {
                    rowContainsFiler = true;
                    continue;
                }
            }
        }
        if (! rowContainsFiler) {
            tr[i].style.display = "none";
        }
        else {
            tr[i].style.display = "";
        }
    }
}
