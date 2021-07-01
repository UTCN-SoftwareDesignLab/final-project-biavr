<template>
  <v-card>
    <v-card-title>
      Users
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
      @click:row="rowClicked"
    ></v-data-table>
    <UserDialog
        :opened="dialogVisible"
        :user="selectedUser"
        @refresh="refreshList"
    ></UserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import UserDialog from "../components/UserDialog"

export default {
  name: "UserList",
  components: {UserDialog},
  data() {
    return {
      users: [],
      selectedUser: {},
      dialogVisible: false,
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
    };
  },
  methods: {
    async refreshList() {
      this.dialogVisible = false;
      this.selectedUser = {};
      this.users = await api.users.allUsers();
    },
    rowClicked(user){
      this.selectedUser = user,
      this.dialogVisible = true;
    }
  },
  async created() {
    // const user = JSON.parse(localStorage.getItem("user"))
    // console.log(user)
    this.users = await api.users.allUsers();
  },
};
</script>

<style scoped></style>
