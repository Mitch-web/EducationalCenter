<img src="" />


<script>
    let img = document.querySelector('img');
    let baseImg = localStorage.getItem('base64Img');
    img.setAttribute('src', baseImg);
    localStorage.removeItem('base64Img');
</script>