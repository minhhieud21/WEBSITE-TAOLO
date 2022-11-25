import React, { useContext, useEffect, useMemo, useState } from "react";
import ChartistGraph from "react-chartist";
// react-bootstrap components
import {
  Badge,
  Button,
  Card,
  Navbar,
  Nav,
  Table,
  Container,
  Row,
  Col,
  Form,
  OverlayTrigger,
  Tooltip,
} from "react-bootstrap";
import { AdminContext } from "layouts/Admin";
import { getStatistical } from "services";
import { getAllProduct } from "services";
import { formatVnd } from "services";
import Loading from "components/Loading";

function Dashboard() {

  const { token, produc } = useContext(AdminContext)
  const [statistical, setStatistical] = useState([])
  const [allProductSold, setAllProductSold] = useState(0)
  const [revenue, setRevenue] = useState([])
  const [product, setProduct] = useState([])
  const [topProductSold, setTopProductSold] = useState([])
  const [isLoading, setIsLoading] = useState(false)

  useEffect(() => {
    setIsLoading(true)
    getStatistical(token).then(data => {
      setStatistical(data.data.data)
      setRevenue(data.data.data.tongdoanhthutheoquy)
      setAllProductSold(data.data.data.tongsoluongdaban)
      setTopProductSold(data.data.data.topsanphambanchay)
    }).catch(e => console.log(e)).finally(() => setIsLoading(false))
    getAllProduct(token).then(res => {
      setProduct(res.data.data.content)
    }).catch(e => console.log(e)).finally(() => setIsLoading(false))

  }, [])

  const totalRevenue = useMemo(() => {
    return revenue.reduce((a, b) => a + b, 0)
  }, [statistical])

  const topRevenue = useMemo(() => {
    return Math.max(...revenue)
  }, [statistical])


  return (
    <>
      {isLoading && <Loading />}

      <Container fluid>
        <Row>
          <Col lg="4" sm="12">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="5">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-chart text-warning"></i>
                    </div>
                  </Col>
                  <Col xs="7">
                    <div className="numbers">
                      <p className="card-category">Revenue</p>
                      <Card.Title as="h4">{formatVnd(totalRevenue)}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats">
                  <i className="fas fa-redo mr-1"></i>
                  Revenue this year
                </div>
              </Card.Footer>
            </Card>
          </Col>

          <Col lg="4" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="5">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-light-3 text-success"></i>
                    </div>
                  </Col>
                  <Col xs="7">
                    <div className="numbers">
                      <p className="card-category">Top revenue</p>
                      <Card.Title as="h4">{topRevenue > 0 && formatVnd(topRevenue)}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats">
                  <i className="far fa-calendar-alt mr-1"></i>
                  Top revenue
                </div>
              </Card.Footer>
            </Card>
          </Col>
          <Col lg="4" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="5">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-vector text-danger"></i>
                    </div>
                  </Col>
                  <Col xs="7">
                    <div className="numbers">
                      <p className="card-category">Total orders</p>
                      <Card.Title as="h4">{allProductSold}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats">
                  <i className="far fa-clock-o mr-1"></i>
                  Total this year
                </div>
              </Card.Footer>
            </Card>
          </Col>
        </Row>
        <Row>
          <Col md="12">
            <Card className="card-tasks">
              <Card.Header>
                <Card.Title as="h4">Top sales</Card.Title>
              </Card.Header>
              <Card.Body>
                <div className="table-full-width">
                  <Table>
                    <tbody>
                      {product && product.slice(6).map((el) =>
                        <tr>
                          <td>
                            {el.proName}
                          </td>

                        </tr>
                      )}
                    </tbody>
                  </Table>
                </div>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats">
                  <i className="now-ui-icons loader_refresh spin"></i>
                  Top product sale in this quarter
                </div>
              </Card.Footer>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default Dashboard;
