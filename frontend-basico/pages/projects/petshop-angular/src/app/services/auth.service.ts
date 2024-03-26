import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { UserLogin } from '../model/userLogin';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user: any;
  errorMessage: string = '';

  private userSubject = new BehaviorSubject<any>(null);

  constructor(private httpClient: HttpClient, private afAuth: AngularFireAuth, private router: Router) { }

  async login(userLogin : UserLogin) {
    this.errorMessage = '';
    try {
      const credential = await this.afAuth.signInWithEmailAndPassword(userLogin.email, userLogin.password);

      this.user = credential.user;

      this.setUserSubject(this.user);

      return credential.user;

    } catch (error: any) {
      if (error instanceof Error) {
        this.errorMessage = error.message;
      } else {
        this.errorMessage = 'Erro desconhecido ao fazer login';
      }
      return error;
    }
  }

  async register(userLogin: UserLogin) {
    this.errorMessage = '';
    try {
      const credential = await this.afAuth.createUserWithEmailAndPassword(userLogin.email, userLogin.password);

      this.user = credential.user;

      this.setUserSubject(this.user);

      return credential.user;

    } catch (error: any) {
      if (error instanceof Error) {
        this.errorMessage = error.message;
      } else {
        this.errorMessage = 'Erro desconhecido ao fazer registro';
      }
      return error;
    }
  }

  private setUserSubject(user: any) {
    sessionStorage.setItem('user', JSON.stringify(user));
    this.userSubject.next(user);
  }

  getUserSubject() {
    return this.userSubject.asObservable();
  }

  getRoles() {
    return this.userSubject.getValue();
  }

  getErrorMessage() {
    return this.errorMessage;
  }

  async logout() {
    try {
      await this.afAuth.signOut();
      this.userSubject.next(null);
      sessionStorage.clear();
      this.router.navigate(['/login']);
    } catch (error) {
      console.error('Erro ao fazer logout:', error);
    }
  }

  static isAuthenticated(): boolean {
    const user = sessionStorage.getItem('user');
    return !!user; // Retorna true se houver um usuário autenticado, caso contrário, retorna false
  }
}
