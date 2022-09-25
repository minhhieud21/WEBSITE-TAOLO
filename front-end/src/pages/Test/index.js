import { connect } from "react-redux";
import ContainerTest from "./ContainerTest";

import { fetchApiPoke } from "../../store/features/test/action";
import { getPoke } from "../../store/features/test/selecter";

const mapStateToProps = (state) => ({
  poke: getPoke(state),
});

const mapDispatchToProps = { fetchApiPoke };

export default connect(mapStateToProps, mapDispatchToProps)(ContainerTest);
