<!-- 
  顶部导航栏布局
 -->
<template>
  <div class="top-head">
    <SearchBar></SearchBar>
    <LoginBtn v-if="username == ''"></LoginBtn>
    <UserLink v-if="username != ''" :username="username" :type="type"></UserLink>
  </div>
</template>

<script>
import SearchBar from '@/components/rightPart/header/search/Search_bar.vue'
import LoginBtn from '@/components/rightPart/header/user/Login_btn.vue'
import UserLink from '@/components/rightPart/header/user/User_link.vue'
import { EventBus } from '@/bus/Event_Bus'
export default {
    name : "hearder_View",
    components : {
        SearchBar, LoginBtn, UserLink
    },
    data() {
      return {
        username : "",
        type : -1
      }
    },
    created() {
      EventBus.$on("local_user", (payload) => {
        this.username = payload.username;
        this.type = payload.type;
      })
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
