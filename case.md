### **Case Study: Migrating Experian AutoCheck from On-Prem to Cloud**  

#### **Problems:**  
The legacy on-prem system was outdated, leading to **performance issues, high maintenance costs, and security vulnerabilities**. The UI was slow and unresponsive, lacking mobile support, while the backend was built on a **monolithic architecture**, making scalability and updates difficult. Security compliance was a major concern due to **OWASP vulnerabilities, weak authentication mechanisms, and the absence of role-based access controls (RBAC)**. Additionally, **manual deployments and lack of CI/CD automation** caused frequent errors and delays. On-prem infrastructure also resulted in **high operational costs**, limited system availability, and **poor monitoring/logging capabilities**, making issue resolution slow.  

#### **Approach:**  
- **Technology Modernization:** Migrated frontend to **React** and backend to **Spring Boot (Java 17)** for better performance and maintainability.  
- **Cloud Adoption:** Deployed services on **AWS ECS and EC3**, used **AWS RDS for database**, and **CloudWatch for monitoring**.  
- **Security Enhancements:** Implemented **SSO authentication using Okta**, enforced **RBAC**, and addressed **OWASP vulnerabilities**.  
- **Performance Optimization:** Optimized **VIN search APIs**, introduced **Redis caching**, and implemented **API rate limiting**.  
- **Automation & DevOps:** Set up **CI/CD pipelines (GitHub Actions/Jenkins)**, introduced **Terraform for Infrastructure as Code (IaC)**, and implemented **automated testing**.  
- **Compliance & Email Handling:** Used **on-prem email relay** instead of AWS SES to meet Experian security policies.  

Would you like me to refine this further or add any specific details? 🚀
