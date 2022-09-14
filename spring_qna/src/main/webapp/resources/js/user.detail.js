document.getElementById('userRemove').addEventListener('click',(e)=>{
    e.preventDefault();
    const idVal = document.getElementById('idVal').innerText;
    const delForm = document.getElementById('userRmForm');
    const inputed = document.getElementById('id');
    inputed.value = idVal;
    delForm.setAttribute('action', '/user/remove')
    delForm.submit();
})