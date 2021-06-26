<template>
<div>
    <v-card>
        <v-text-field v-model="company.name" label="Name" v-bind:readonly = "this.editEnabled" />
        <v-text-field v-model="company.description" label="Description" v-bind:readonly = this.editEnabled />
    <v-card-actions>
        <v-btn @click="edit" v-show="isRecruiter"> Edit </v-btn>
        <v-btn @click="save" v-show="isRecruiter" > Save </v-btn>
    </v-card-actions>
    </v-card>
</div>
</template>

<script>
import api from "../api";

export default {
  name: "CompanyDetails",
  props: {
    company: Object,
  },
  data(){
      return {
          editEnabled : true,
          isRecruiter : false
      };
  },
  methods: {
    edit(){
        this.editEnabled = false;
    },
    save() {
        this.editEnabled = true;
        api.companies
        .edit({
        id: this.company.id,
        name: this.company.name,
        description: this.company.description,
        jobs: this.company.jobs,
        users: this.company.users
        })
        .then(() => this.$emit("refresh"));
    },
    
  },
  created() {
        const user = JSON.parse(localStorage.getItem("user"));
        const role = user.roles[0];
        console.log("In CompanyDetail I am ",role);
        if(role == "SEEKER"){
            this.isRecruiter = false
        }
        else{
            this.isRecruiter = true
        }
    }
};
</script>

<style scoped></style>
