const uploadForm = document.querySelector('.upload')
uploadForm.addEventListener('submit', function(e) {
   e.preventDefault()
   let file = e.target.uploadFile.files[0]
})
