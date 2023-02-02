import { GeoLocation } from './geo-location';

export interface Address {
  street: string;
  housenumber: string;
  postalcode: string;
  city: string;
  geoLocation: GeoLocation;
}
