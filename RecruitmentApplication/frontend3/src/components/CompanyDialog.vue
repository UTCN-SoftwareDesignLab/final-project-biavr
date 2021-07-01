<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create company" : "Edit company" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="company.name" label="Name"/>
          <v-text-field v-model="company.description" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteCompany" v-show="!isNew"> Delete </v-btn>
          <!-- <v-btn @click="addRecruiter" > Add Recruiter </v-btn> -->
          <v-btn @click="setInvisible"> Close </v-btn>
        </v-card-actions>
        <v-data-table v-show="newRecruiters"
          :headers="headers"
          :items="users"
          :search="search"
          @click:row="rowClicked"
    ></v-data-table>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "CompanyDialog",
  props: {
    company: Object,
    opened: Boolean,
  },
  data(){
    return {
      newRecruiters: false,
      users: [],
      selectedUser: {},
      search: "",
      headers: [
        {
          text: "Username",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Email", value: "email" },
        { text: "Roles", value: "roles" },
      ],
    }
  },
  methods: {
    persist() {        
      if (this.isNew) {
        api.companies
          .create({
            name: this.company.name,
            description: this.company.description,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.companies
          .edit({
            id: this.company.id,
            name: this.company.name,
            description: this.company.description,
            jobs: this.company.jobs,
            users: this.company.users
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteCompany(){
      api.companies
        .delete({
          id: this.company.id,
      })
      .then(() => this.$emit("refresh"));
    },
    async addRecruiter(){
      this.newRecruiters=true;
      this.users = await api.users.allSeekers();
      console.log(this.users);
      console.log("Add a new recruiter for this company");
    },
    rowClicked(user){
      let compUsers = this.company.users;
      console.log(this.company);
      compUsers.push(user);
      
      api.companies
          .edit({
            id: this.company.id,
            name: this.company.name,
            description: this.company.description,
            jobs: this.company.jobs,
            users: compUsers,
          })
          .then(() => this.$emit("refresh"));
    },
    setInvisible(){
      this.$emit("refresh");
    }
  },
  computed: {
    isNew: function () {
      return !this.company.id;
    },
  },
};
</script>

<style scoped></style>
