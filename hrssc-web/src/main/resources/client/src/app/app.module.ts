import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
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


const appRoutes: Routes = [
  
  {
    path: 'login',
    component: LoginComponent,
    data: { title: 'LoginComponent' }
  },
  {
    path: 'home',
    component: HomeComponent,
    data: { title: 'Heroes List' }
  },
  {
    path: 'manager/resource',
    component: ManageResourcesComponent,
    data: { title: 'Mng' }
  },
  {
    path: 'manager/invitations',
    component: ManageInvitationsComponent,
    data: { title: 'Mng' }
  },
 
  { path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  { path: '**',
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
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    )
  ],
  providers: [AppComponent,],
  bootstrap: [AppComponent]
})
export class AppModule { }
