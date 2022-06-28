function fetch_delete(id) {
    let url = "/admin/delete?id=" + id;
    fetch(url)
        .then((response) => {
            change_tab(1);
            $("#deletingModal").modal("hide")
        })
        .catch(function (err) {
            console.error('Fetch Error :-S', err);
        });
}
function fetch_edit(user) {
    let formdata = $("#editForm").serialize();
    let url = "/admin/edit?" + formdata;
    fetch(url)
        .then((response) => {
            change_tab(1);
            $("#editingModal").modal("hide")
        })
        .catch(function (err) {
            console.error('Fetch Error :-S', err);
        });
}
function fetch_add(form) {
    let formdata = $("#js_new_user_form").serialize();
    let url = "/admin/add_user?" + formdata;
    fetch(url)
        .then((response) => {
            change_tab(1);
        })
        .catch(function (err) {
            console.error('Fetch Error :-S', err);
        });
}