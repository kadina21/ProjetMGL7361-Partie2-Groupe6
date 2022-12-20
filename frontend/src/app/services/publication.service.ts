import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Publication } from '../models/publication';

@Injectable({
  providedIn: 'root'
})
export class PublicationService {

  REST_API: string = environment.ENDPOINTS.api
  publicationsData : Publication[]=[];
  constructor(private http: HttpClient) { }

  private log(log: string) {
    console.info(log);
  }

  private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error);
			console.log(`${operation} failed: ${error.message}`);

			return of(result as T);
		};
	}


  searchePub(term :string): Observable<Publication[]>{
    if(!term.trim()){
      return of([]);
    }
    return this.http.get<Publication[]>(`${this.REST_API}/articles?titre=${term}`).pipe(
      tap(_=>this.log(`found articles matching "${term}"`)),
      catchError(this.handleError<Publication[]>('searchUser',[]))
    );
  }

  getPublications() {
    return this.http.get<Publication[]>(`${this.REST_API}/articles`)
  }
  getPub(id: string): Observable<Publication> {
    const url = `${this.REST_API}/article/${id}`;
    return this.http.get<Publication>(url).pipe(

      catchError(this.handleError<any>('getUser'))
    );

  }

  updatePub(id:any, publication:Publication): Observable<any> {
    let API_URL = `${this.REST_API}/article/${id}`;
    return this.http.put(API_URL,publication)
      .pipe(
        catchError(this.handleError<any>('updateUser'))
      )
  }

  deletePub(id: string): Observable<Publication> {
    const url = `${this.REST_API}/article/${id}`;
    return this.http.delete<Publication>(url).pipe(
      catchError(this.handleError<any>('deleteUser'))
    )
  }

}
