import KeyHighlights from "./components/layout/KeyHighlights";
import ListBooks from "./components/layout/ListBooks";
import Slider from "./components/layout/Slider";

export default async function Home() {
  const res = await fetch(`${process.env.API_URL}/books`);
  const books = await res.json();
  return (
    <div>
      <Slider></Slider>
      <KeyHighlights></KeyHighlights>
      <ListBooks books={books}></ListBooks>
      <ListBooks books={books}></ListBooks>
    </div>
  );
}
