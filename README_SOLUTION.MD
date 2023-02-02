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

TO DO



---
