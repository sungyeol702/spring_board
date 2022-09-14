document.getElementById('boardRemove').addEventListener('click',(e)=>{
    e.preventDefault();
    const bnoVal = document.getElementById('bnoVal').innerText;
    const inputed = document.getElementById('bno');
    const formTag = document.getElementById('boardRmForm') ;
    formTag.setAttribute('action','/board/remove');
    inputed.value = bnoVal;
    formTag.submit();
});