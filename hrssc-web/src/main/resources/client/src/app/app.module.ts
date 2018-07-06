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
import { CompaniesService } from './services/companies.service';
import { ChooseDomainService } from './services/choose-domain.service';
import { ChooseDomain } from './models/chooseDomain.model';
import { ProjectService } from './services/project.service';
import { ScriptLoadService } from './services/script-load.service';
import { ManagementService } from './services/management.service';
import { AccountManagerPopoverComponent } from './pages/chef-manage-account-manager/account-manager-popover.component';
import { ResourceManagerPopoverComponent } from './pages/manage-resources/resource-manager-popover.component';
import { ResourceSimilarComponent } from './pages/resource-info/resource-similar/resource-similar.component';
import { ProjectMatchingComponent } from './pages/resource-info/project-matching/project-matching.component';
import { InfoResourceViewComponent } from './pages/resource-info/info-resource-view/info-resource-view.component';
import { InfoResourceManagerComponent } from './pages/resource-info/info-resource-manager/info-resource-manager.component';
import { RatingResourceComponent } from './pages/resource-info/rating-resource/rating-resource.component';

import { SelectModule } from 'ng-select';
import { MyDatePickerModule } from 'angular4-datepicker/src/my-date-picker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InfoProjectManagerComponent } from './pages/project-info/info-project-manager/info-project-manager.component';
import { InfoProjectViewComponent } from './pages/project-info/info-project-view/info-project-view.component';
import { ResourceMatchingComponent } from './pages/project-info/resource-matching/resource-matching.component';
import { ProjectInfoPopupComponent } from './pages/project-info/project-info-popup/project-info-popup.component';
import { InviteResourcePopover } from './pages/resource-info/invite-resource-popover/invite-resource-popover.component';
import { InvitationService } from './services/invitation.service';

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
    path: 'cheft/manage/account',
    component: ChefManageAccountManagerComponent,
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
    ResourceManagerPopoverComponent,
    ManageProjectsComponent,
    ManageAppliancesComponent,
    AdminManageCompanyComponent,
    AdminManageAccountRequestComponent,
    ChefManageAccountManagerComponent,
    AccountManagerPopoverComponent,
    ResourceSimilarComponent,
    ProjectMatchingComponent,
    InfoResourceViewComponent,
    InfoResourceManagerComponent,
    RatingResourceComponent,
    FeedbackComponent,
    RegisterComponent,
    PageHeaderComponent,
    InfoProjectManagerComponent,
    InfoProjectViewComponent,
    ResourceMatchingComponent,
    ProjectInfoPopupComponent,
    InviteResourcePopover
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    HttpModule,
    StorageServiceModule,
    FormsModule,
    CustomFormsModule,
    ReactiveFormsModule,
    MomentModule,
    SelectModule,
    MyDatePickerModule,
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
    Register,
    CompaniesService,
    ChooseDomainService,
    ChooseDomain,
    ProjectService,
    ScriptLoadService,
    ManagementService,
    InvitationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
