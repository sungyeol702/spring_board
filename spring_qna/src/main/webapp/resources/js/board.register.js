const regExpPrevent = new RegExp("\.(exe|sh|bat|js|msi|dll)$"); 
const regExpImage = new RegExp("\.(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$");
const maxSize = 2 * 1024 * 1024
function fileSizeAndTypeValidation(fileName, fileSize) {
    if(regExpPrevent.test(fileName)){
        return 0;
    }else if(!regExpImage.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}
document.addEventListener('change', (e) => {//이미지에 파일이 첨부되어 바뀐상황
    e.preventDefault();
    if (e.target.id == 'images') {
        document.getElementById('regBtn').disabled = false;
        //input type file element에 저장된 file 정보를 가져오는 property, 리턴은 객체형
        const fileObjects = document.getElementById('images').files;

        let div = document.getElementById('imageZone');
        div.innerHTML = '';
        let ul = '<ul class="list-group list-group-flush">';
        let isOk = 1;
        for (const file of fileObjects) {
            let vaildFile = fileSizeAndTypeValidation(file.name, file.size);
            isOk *= vaildFile;
            ul += `<li class="${vaildFile ? "bg-success text-white":"bg-danger text-white"} list-group-item d-flex justify-content-between align-items-start">`;
            ul += `<div class="ms-2 me-auto">${file.name}</div>`;
            ul += `<span class="badge bg-primary rounded-pill">${file.size}</span></li>`;
        }
        ul += '</ul>';
        div.innerHTML = ul;
        if(!isOk){
            document.getElementById('regBtn').disabled = true;
        }

    }
})