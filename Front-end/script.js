function openModal() {
	var modal = document.getElementById("myModal");
	modal.style.display = "block";
}

function closeModal() {
	var modal = document.getElementById("myModal");
	modal.style.display = "none";
}

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
