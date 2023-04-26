import React from "react";
import { Container } from "react-bootstrap";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import CustomerContainer from "../../container/customer/CustomerContainer";

function CustomerPages() {
  return (
    <Container>
      <Header />
      <CustomerContainer />
      <Footer />
    </Container>
  );
}
export default CustomerPages;