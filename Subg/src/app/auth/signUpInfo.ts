export class SignUpInfo{
    fname:string;
    lname:string;
    password:string;
    repassword:string;  
    phone:string;
    email:string;  
    role:string[]; 


constructor( fname:string,lname:string,password:string,phone:string,email:string){
    this.fname=fname;
    this.lname=lname;
    this.password=password;
    this.phone=phone;
    this.email=email;
    this.role=['user'];
}
}