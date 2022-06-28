function modalEdit(id) {
    let div = document.getElementById("editFormContainer");
    div.innerHTML="";
    let form = document.createElement("div");
    fetch("/admin/redact?id=" + id)
        .then(response => response.text())
        .then(content => form.innerHTML = content);
    div.append(form);
}
function modalDelete(id) {
    let div = document.getElementById("deleteFormContainer");
    div.innerHTML="";
    let form = document.createElement("div");
    fetch("/admin/delete_form?id=" + id)
        .then(response => response.text())
        .then(content => form.innerHTML = content);
    div.append(form);
}
function change_tab(tab_id) {
    if (tab_id == 2) {
        let div = document.getElementById('js-tab');
        div.innerHTML="";
        let tab = document.createElement("div");
        fetch("/admin/adding_form")
            .then(response => response.text())
            .then(content => tab.innerHTML = content);
        div.append(tab);
        document.getElementById("tab-1").classList.remove("active");
        document.getElementById("tab-2").classList.add("active");
    }
    if (tab_id == 1) {
        let div = document.getElementById('js-tab');
        div.innerHTML="";
        let tab = document.createElement("div");
        fetch("/admin/main")
            .then(response => response.text())
            .then(content => tab.innerHTML = content);
        div.append(tab);
        document.getElementById("tab-2").classList.remove("active");
        document.getElementById("tab-1").classList.add("active");
    }
}