import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from './user';
import { throwError } from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(user:User) {
    return this.http.post('http://localhost:9090/login',user,{responseType:'text' as 'json'}).
    pipe(catchError(this.handleError));

  }
  private handleError(error: HttpErrorResponse){
    // if(error.error instanceof ErrorEvent){
    //   console.error('Client Side Error: ',error.error.message);
    // } 
    // else{
    //   console.error('Server Side Error: ',error.error.message);
    // }
     return throwError(error.error.message || "Server Error");
   
  }
}
