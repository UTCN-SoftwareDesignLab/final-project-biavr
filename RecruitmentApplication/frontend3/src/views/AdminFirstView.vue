<template>
  <div>
    <v-card>
      <v-card-title>
        Admin
        <v-spacer></v-spacer>
        <v-btn @click="goToUsers">Users</v-btn>
        <v-btn @click="goToCompanies">Companies</v-btn>
      </v-card-title>
    </v-card>
  </div>
</template>

<script>

export default {
  name: "AdminFirstView",
  methods: {
    goToUsers(){
    this.$router.push({name:"Users"});
    },
    goToCompanies(){
        this.$router.push({name:"Companies"});
    }
  },
  
};
</script>

<style scoped></style>
