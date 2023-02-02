# Explaining the Solution and how it was implemented

---
**Architectural Solution of the project.**

The project is composed of two main layers, one in Front-End written in Angular 13 and another in Back-End written in DropWizard.

*Java Back-End*

The design choice fell on DropWizard as the basic service was released on DropBox.

The ARMsList Application class is loaded by the system which initializes the CORS permissions for the other infrastructure.
A health status service called TemplateHealthCheck is also used.

Monitoring and observability are essential in distributed environments and they rely on effective health checking mechanisms that can be observed at runtime.

We also learn to predict the system health in the future by observing any anomalies in a series of metrics like memory utilization, errors, and disk space. This allows us to take mitigating actions like restarting instances, falling back to a redundant instance, or throttling the incoming requests.

The ATMsListConfiguration class defines the configuration of the DropWizard template (example the default name included in the ATMS.yml file).

Two service APIs have been developed: /fullsearch and /searchbyfield. Only the first is used in the Angular project, but the second was developed for completeness making use of Java 8 filters. A possible extension of the project is to create a mask based on each single search field and not fulltext.

*Angular Front-End*

The TypeScript language is used in the project. 
The app.module.ts file imports the modules of the form, of the navigation routes, of the HTTP API calls and of the components used at the Front-End level.

Initially a file was developed to hold the configuration constants in a global variable called GlobalConstants in the constants.ts file.

To start designing while the "Back-End Team" is developing the REST API in parallel, a mocked data file called mock-atms-list has been created.

To implement the real-time visualization starting from the data sending of the search box, the "Reactive Programming" typical of Angular has been adopted.

Here are the benefits of writing our templates in this more responsive style:

1. There are no manual component-level subscriptions for observables that leave the service tier;
2. You don't need to create smaller components to be able to use the asynchronous pipe only once and prevent multiple subscriptions;
3. no local data state variables are defined at the component level, so problems caused by local component state mutation are less likely to occur;
4. now we have more declarative code: both the component and the model are very declarative, we are just linking data streams together instead of storing local variables and passing them to the model;


---
