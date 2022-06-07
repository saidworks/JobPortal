export class User{
  username: string;
  firstName: string;
  lastName: string;
  course:string;
  campus:string;
  email: string;
  password: string;
  roles: string[];
  constructor(username: string,firstName:string,lastName:string,
              course:string,campus:string,email: string,
              password: string, roles: string[]) {
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.course = course;
      this.campus = campus;
      this.email = email;
      this.password = password;
      this.roles  = roles;
  }
}
