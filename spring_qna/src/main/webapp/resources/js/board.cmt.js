async function postCommentToServer(cmtData) {
    try {
        const url = '/cmt/post';
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtSbmBtn').addEventListener('click', (e) => {
    const cmtInputObj = document.getElementById('cmtText');
    if (cmtInputObj.value == null || cmtInputObj.value == '') {
        alert('댓글 내용을 입력해 주세요!');
        cmtInputObj.focus();
        return false;
    } else {
        let cmtData = {
            bno: document.getElementById('bnoVal').innerText,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtInputObj.value
        };
        postCommentToServer(cmtData).then(result => {
            if (parseInt(result)) {
                alert('댓글 등록 성공');
                cmtInputObj.value = "";
                getCommentList(document.getElementById('bnoVal').innerText);
            }
        });
    }
});

async function spreadCommentFromServer(bnoVal,pageNo) {
    try {
        const resp = await fetch('/cmt/' + bnoVal+'/'+pageNo ); 
        const pagingHandler = await resp.json();
        return await pagingHandler;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bnoVal,pageNo = 1) {
    spreadCommentFromServer(bnoVal,pageNo).then(result => {
        document.getElementById('cmtZone').innerHTML= '';
        console.log(result);
        document.getElementById('total').innerText = result.totalCount;
        const sesWritrt = document.getElementById('cmtWriter').innerText;
        if (result.list.length && pageNo == 1) {
            let tag='';
            for (const cvo of result.list) {
                tag +=`<div class="card">`;
                tag += '<div class="card-body">';
                tag += `<h4 class="card-title">${cvo.writer}</h4>`
                tag += `<p class="card-text contentVal">${cvo.content}</p>`
                tag +=`<p class="card-text">${cvo.modAt}</p>`
                 if (sesWritrt == cvo.writer) {
                     tag +=`<button class="btn btn-sm btn-warning cmtMod" data-cno="${cvo.cno}">E</button>`;
                     tag += `<button class="btn btn-sm btn-danger cmtDel" data-cno="${cvo.cno}">X</button>`;
                 }
                 tag +=`<button type="button" class="btn btn-sm btn-warning repBtn" data-cno="${cvo.cno}">답글</button>`
                 tag +='</div></div>'
                }
                document.getElementById('cmtZone').innerHTML = tag;
        } else if(pageNo>1) {
            let tag='';
            for (const cvo of result.list) {
                tag+=`<div class="card">`;
                tag += '<div class="card-body">';
                tag += `<h4 class="card-title">${cvo.writer}</h4>`
                tag += `<p class="card-text contentVal">${cvo.content}</p>`
                tag +=`<p class="card-text">${cvo.modAt}</p>`
                 if (sesWritrt == cvo.writer) {
                     tag +=`<button class="btn btn-sm btn-warning cmtMod" data-cno="${cvo.cno}">E</button>`;
                     tag += `<button class="btn btn-sm btn-danger cmtDel" data-cno="${cvo.cno}">X</button>`;
                 }
                 tag +=`<button type="button" class="btn btn-sm btn-warning repBtn">열기</button>`
                 tag +='</div></div>';
                }
                document.getElementById('cmtZone').innerHTML += tag;
        }else {
            document.getElementById('cmtZone').innerText = "comment가 없습니다."
        }
        const moreBtn = document.getElementById('moreBtn');
        if(pageNo < parseInt(Math.ceil(result.totalCount/10.0))){
            moreBtn.style.visibility='visible';
            moreBtn.dataset.page = parseInt(moreBtn.dataset.page)+1;
        }else{
            moreBtn.style.visibility='hidden';
        }
    });
}
//mod
async function commentUpdateToServer(cmtData) {
    try {
        const url = "/cmt/"+cmtData.cno;
        const config = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click',(e)=>{
    if (e.target.classList.contains('cmtMod')) {
        const div = e.target.closest('div');
        const contentTag =div.children[1];
        console.log(contentTag);
        const contentVal =contentTag.innerText;
        contentTag.innerHTML = `<div class="input-group mb-3" id ="cmt">
        <input type="text" class="form-control cmtModVal"  value="${contentVal}">
       <button type="button" class="btn btn-primary modSbmBtn" data-cno="${e.target.dataset.cno}" id="ModBtn">MOD</button>
   </div>`
    }
    if(e.target.classList.contains('modSbmBtn')){
        const cmtModInput= document.querySelector('.cmtModVal')
        if (cmtModInput.value == '') {
            alert('수정할 댓글 내용을 입력하세요!');
            cmtModInput.focus();
            return false;
        }else {
            const cmtData = {
                cno: e.target.dataset.cno,
                content: cmtModInput.value
            };
            console.log(e.target.dataset.cno)
            commentUpdateToServer(cmtData).then(result => {
                if (parseInt(result)) {
                    alert('수정완료');
                    getCommentList(document.getElementById('bnoVal').innerText);
                }
            })
        }
    }
    if (e.target.classList.contains('cmtDel')) {
        const cnoVal = e.target.dataset.cno;
        const bnoVal = document.getElementById('bnoVal').innerText;
        commentRemoveFromServer(cnoVal, bnoVal).then(result => {
            if (parseInt(result)) {
                alert('댓글 삭제 완료')
                getCommentList(document.getElementById('bnoVal').innerText);
            }
        })
    }
    if(e.target.id == 'moreBtn'){
        e.preventDefault();
        const bnoVal = document.getElementById('bnoVal').innerText;
        const page = e.target.dataset.page ;
        getCommentList(bnoVal, parseInt(page)+1);
        
    }

})

async function commentRemoveFromServer(cnoVal,bnoVal) {
    try {
        const url = '/cmt/' + cnoVal+'/'+bnoVal;
        const config = {
            method: 'DELETE'
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result

    } catch (error) {
        console.log(error);
    }
}





