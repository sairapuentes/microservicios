import { HttpInterceptorFn } from "@angular/common/http";

export const autenticacionInt: HttpInterceptorFn = (req, next) => {
    const token = localStorage.getItem('token');
    const idSede = localStorage.getItem('idSede');

    if(!token){
        return next(req)
    }

    const requestToken = req.clone({
        setHeaders:{
            Authorization: `Bearer ${token}`,
            ...(idSede ? {'X-Usuario-Sede' : idSede} : {})
        }
    });
    return next(requestToken);
};