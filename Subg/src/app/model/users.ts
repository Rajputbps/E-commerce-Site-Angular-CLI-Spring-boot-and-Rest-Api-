export class Users{
    private fname:string;
    private lname:string;
    private password:string;
    private phone:string;
    private email:string;
    private address:Address[]  
}

export class Address{
    addressId:number
    name:string
    private streetAddress1:string
    private streetAddress2:string
    private phoneNumber:number
    private city:string
    private pinCode:number
    private state:string
}