// FORMS DO CADASTRO DE AÇÕES
function openModal() {
	var modal = document.getElementById("myModal");
	modal.style.display = "block";
}

function closeModal() {
	var modal = document.getElementById("myModal");
	modal.style.display = "none";
}

// BARRA DE PESQUISA
function toggleSearch() {
	var searchBox = document.getElementById("searchBox");
	var openSearchBox = document.getElementById("openSearchBox");
	var closeSearchBox = document.getElementById("closeSearchBox");
	if (searchBox.style.display === "flex") {
		searchBox.style.display = "none";
		openSearchBox.style.display = "flex";
		closeSearchBox.style.display = "none";
	} else {
		searchBox.style.display = "flex";
		openSearchBox.style.display = "none";
		closeSearchBox.style.display = "flex";
	}
}

// PÁGINA DA LISTA DE AÇÕES SOCIAIS
document
	.getElementById("fetchSocialActions")
	.addEventListener("click", openSocialActionsListPage);

function openSocialActionsListPage() {
	var socialActionsList = document.getElementById("socialActionsList");
	var container = document.getElementById("container");
	socialActionsList.style.display = "flex";
	container.style.display = "none";
}

document
	.getElementById("backToMainPage")
	.addEventListener("click", closeSocialActionsListPage);

function closeSocialActionsListPage() {
	var socialActionsList = document.getElementById("socialActionsList");
	var container = document.getElementById("container");
	socialActionsList.style.display = "none";
	container.style.display = "block";
}

// PEGA TODAS AS AÇÕES SOCIAIS
document
	.getElementById("fetchSocialActions")
	.addEventListener("click", getSocialActions);

function getSocialActions() {
	const url = "http://localhost:8081/socialactions";

	const requestOptions = {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
		},
	};

	fetch(url, requestOptions)
		.then(response => response.json())
		.then(response => {
			data = response;
			console.log(data);

			data.forEach(item => {
				console.log(item);
			});
		})
}
