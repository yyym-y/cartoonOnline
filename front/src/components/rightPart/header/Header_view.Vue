<template>
  <div class="top-head">
    <SearchBar></SearchBar>
    <LoginBtn></LoginBtn>
  </div>
</template>

<script>
import SearchBar from './util/Search_bar'
import LoginBtn from './util/Login_btn'

export default {
    name : "hearder_View",
    components : {
        SearchBar, LoginBtn
    }
}
</script>

<style scoped>
.top-head {
    height: 97%;
    border-bottom-style: solid;
    border-bottom-color: #C0C4CC;
    border-bottom-width: 1px;
}
</style>
