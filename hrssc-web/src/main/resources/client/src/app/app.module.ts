import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import {MomentModule} from 'angular2-moment/moment.module';

import { StorageServiceModule} from 'angular-webstorage-service';
import { CustomFormsModule } from 'ng2-validation';

import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MenuLeftComponent } from './components/menu-left/menu-left.component';
import { LoginComponent } from './pages/login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { ChooseDomainComponent } from './pages/choose-domain/choose-domain.component';
import { ResourceInfoComponent } from './pages/resource-info/resource-info.component';
import { ProjectInfoComponent } from './pages/project-info/project-info.component';
import { CompanyInfoComponent } from './pages/company-info/company-info.component';
import { ResourceOverviewComponent } from './components/resource-overview/resource-overview.component';
import { ProjectOverviewComponent } from './components/project-overview/project-overview.component';
import { ManageInvitationsComponent } from './pages/manage-invitations/manage-invitations.component';
import { ManageResourcesComponent } from './pages/manage-resources/manage-resources.component';
import { ManageProjectsComponent } from './pages/manage-projects/manage-projects.component';
import { ManageAppliancesComponent } from './pages/manage-appliances/manage-appliances.component';
import { AdminManageCompanyComponent } from './pages/admin-manage-company/admin-manage-company.component';
import { AdminManageAccountRequestComponent } from './pages/admin-manage-account-request/admin-manage-account-request.component';
import { ChefManageAccountManagerComponent } from './pages/chef-manage-account-manager/chef-manage-account-manager.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { RegisterComponent } from './pages/register/register.component';
import { MenuLeftService } from './components/menu-left/menu-left.component.service';
import { RestService } from './services/rest.service';
import { LoginService } from './services/login.service';
import { EmployeeService } from './services/employee.service';
import { SessionsService } from './services/sessions.service';
import { AuthenticateService } from './services/authenticate.service';
import { User } from './models';
import { PageHeaderComponent } from './components/page-header/page-header.component';
import { RegisterService } from './services/register.service';
import { HeaderService } from './components/header/header.component.service';
import { Register } from './models/register.model';


const appRoutes: Routes = [




  {
    path: 'login',
    component: LoginComponent,
    data: { title: 'LoginComponent' }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: { title: 'Register' }
  },
  {
    path: 'home',
    component: HomeComponent,
    data: { title: 'Home' }
  },
  {
    path: 'chooseDomain',
    component: ChooseDomainComponent,
    data: { title: 'choose domain' }
  },
  {
    path: 'company/info',
    component: CompanyInfoComponent,
    data: { title: 'Company info' }
  },
  {
    path: 'manager/appliance',
    component: ManageAppliancesComponent,
    data: { title: 'manage appliance page for manager' }
  },
  {
    path: 'manager/invitation',
    component: ManageInvitationsComponent,
    data: { title: 'manage invitation page for manager' }
  },
  {
    path: 'manager/resource',
    component: ManageResourcesComponent,
    data: { title: 'manage resource page for manager' }
  },
  {
    path: 'manager/project',
    component: ManageProjectsComponent,
    data: { title: 'manage project page for manager' }
  },
  {
    path: 'manager/project/info',
    component: ProjectInfoComponent,
    data: { title: 'Project info for nomal user watch' }
  },
  {
    path: 'manager/resource/info',
    component: ResourceInfoComponent,
    data: { title: 'Resource info for nomal user watch' }
  },
  {
    path: 'admin/manage/company',
    component: AdminManageCompanyComponent,
    data: { title: 'Resource info for nomal user watch' }
  },
  {
    path: 'admin/manage/request',
    component: AdminManageAccountRequestComponent,
    data: { title: 'Resource info for nomal user watch' }
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    MenuLeftComponent,
    LoginComponent,
    ChooseDomainComponent,
    ResourceInfoComponent,
    ProjectInfoComponent,
    CompanyInfoComponent,
    ResourceOverviewComponent,
    ProjectOverviewComponent,
    ManageInvitationsComponent,
    ManageResourcesComponent,
    ManageProjectsComponent,
    ManageAppliancesComponent,
    AdminManageCompanyComponent,
    AdminManageAccountRequestComponent,
    ChefManageAccountManagerComponent,
    FeedbackComponent,
    RegisterComponent,
    PageHeaderComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpModule,
    StorageServiceModule,
    FormsModule,
    CustomFormsModule,
    ReactiveFormsModule,
    MomentModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    )
  ],
  providers: [
    AppComponent,
    MenuLeftService,
    RestService,
    LoginService,
    SessionsService,
    AuthenticateService,
    EmployeeService,
    User,
    HeaderService,
    RegisterService,
    Register
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
