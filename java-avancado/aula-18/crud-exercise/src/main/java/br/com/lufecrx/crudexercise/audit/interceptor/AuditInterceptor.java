// package br.com.lufecrx.crudexercise.audit.interceptor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

// import br.com.lufecrx.crudexercise.audit.service.AuditService;
// import br.com.lufecrx.crudexercise.auth.model.User;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class AuditInterceptor implements HandlerInterceptor {

//     @Autowired
//     private AuditService auditService;

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//             throws Exception {
//         String origin = request.getHeader("X-FORWARDED-FOR"); // Recover the origin of the request
//         if (origin == null) {
//             origin = request.getRemoteAddr();
//         }

//         // Recover the user ID that triggered the event
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         String userId = "unauthenticated user";
//         if (authentication != null && authentication.getPrincipal() instanceof User) {
//             User user = (User) authentication.getPrincipal();
//             userId = user.getUsername();
//         }

//         // Recover the event name from the request
//         String eventName = request.getMethod() + " " + request.getRequestURI();
//         String description = "Request made by " + userId;

//         // Register the event in the audit log
//         auditService.logEvent(eventName, description, userId, request.getRequestURI(), origin);
//         return true;
//     }

//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//             ModelAndView modelAndView) throws Exception {
//     }

//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//             throws Exception {
//     }
// }
