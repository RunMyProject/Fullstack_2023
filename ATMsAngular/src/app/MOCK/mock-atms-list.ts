import { ATM } from '../model/atm';

export const MOCK_ATMs: ATM[] = [
  { address: { street: 'S1', housenumber: 'H1', postalcode: 'P1', city: 'C1', geoLocation: {lat: 'L1', lng: 'LN1'} }, distance : 1, type: 'test_1' },
  { address: { street: 'S2', housenumber: 'H2', postalcode: 'P2', city: 'C2', geoLocation: {lat: 'L2', lng: 'LN2'} }, distance : 2, type: 'test_2' },
  { address: { street: 'S3', housenumber: 'H3', postalcode: 'P3', city: 'C3', geoLocation: {lat: 'L3', lng: 'LN3'} }, distance : 3, type: 'test_3' },
  { address: { street: 'S4', housenumber: 'H4', postalcode: 'P4', city: 'C4', geoLocation: {lat: 'L4', lng: 'LN4'} }, distance : 4, type: 'test_4' },
  { address: { street: 'S5', housenumber: 'H5', postalcode: 'P5', city: 'C5', geoLocation: {lat: 'L5', lng: 'LN5'} }, distance : 5, type: 'test_5' },
]

