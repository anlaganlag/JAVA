<template>
  <div>
    <div class="home-img">
      <img src="../../assets/image/home/welcome.png" alt="">
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'home',
}
</script>

<style lang="scss" scoped>
.home-img{
  width: 100%;
  height: 100%;
  background: #fff;
  padding-top: 200px;
  img{
    display: block;
    margin: 0px auto;
  }
}
</style>
